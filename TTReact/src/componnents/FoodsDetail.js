import { useContext, useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Api, { authApi, endpoints } from "../configs/Api";
import { Alert, Button, Form, Image, ListGroup } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import styles from "./FDeTail.module.css";
import Moment from "react-moment";
import cookie from "react-cookies"
import { MyCartContext, MyUserContext } from "../App";
import Heart from "react-animated-heart";


const FoodsDetail = () => {
  const [, cartDispatch] = useContext(MyCartContext);
  const [user,] = useContext(MyUserContext);
  const { foodId } = useParams();
  const [foods, setFoods] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [content, setContent] = useState('');
  const [rate, setRate] = useState('');
  const [err, setErr] = useState(null);
  const cont = useRef();
  const [isclick, setClick] = useState(false);
  const [currentReviewPage, setCurrentReviewPage] = useState(1);
  const recordsPerReviews = 4;
  const lastIndex = currentReviewPage * recordsPerReviews;
  const firstIndex = lastIndex - recordsPerReviews;
  const records = reviews.slice(firstIndex, lastIndex);
  const npage = Math.ceil(reviews.length / recordsPerReviews);
  const numbers = [...Array(npage + 1).keys()].slice(1);


  useEffect(() => {
    const loadFoods = async () => {
      try {
        let { data } = await Api.get(endpoints["details"](foodId));
        setFoods(data);
      }
      catch (ex) {
        console.error(ex);
      }
    };

    loadFoods();
  }, []);

  useEffect(() => {
    const loadReviews = async () => {
      try {
        let { data } = await Api.get(endpoints['reviews'](foodId));
        setReviews(data);
      }
      catch (ex) {
        console.error(ex);
      }
    };

    loadReviews();
  }, [reviews]);


  const addReview = () => {
    const process = async () => {
      try {
        let { data } = await authApi().post(endpoints['add-review'], {
          "comment": content,
          "foodId": foods.foodId,
          "rating": rate
        });
        setContent([data, ...reviews]);
        setContent('');
        setRate('');
      }
      catch (ex) {
        console.error(ex)
      }
    }

    if (content === '') {
      setErr('invalid comment')
    }
    else if (rate === '') {
      setErr('invalid rating')
    }
    else {
      process();
      cont.current.focus();
    }
  }

  const preCommentPage = () => {
    if (currentReviewPage !== firstIndex) {
      setCurrentReviewPage(currentReviewPage - 1)
    }

  }

  const changeCommentPerPage = (id) => {
    setCurrentReviewPage(id)
  }

  const nextCommentPage = () => {
    if (currentReviewPage !== lastIndex) {
      setCurrentReviewPage(currentReviewPage + 1)
    }
  }


  const order = (foods) => {
    cartDispatch({
      type: "inc",
      payload: 1
    });

    let cart = cookie.load("cart") || {};
    if (foods.foodId in cart) {
      cart[foods.foodId]["quantity"] += 1;
    } else {
      cart[foods.foodId] = {
        id: foods.foodId,
        name: foods.name,
        quantity: 1,
        unitPrice: foods.price
      }
    }
    cookie.save("cart", cart);
    console.info(cart);
  }

  const handleBlur = (event) => {
    if (event.target.value < 0 || event.target.value > 10) {
      alert("Vui lòng nhập số từ 0 đến 10");
      setRate(0);
    }
  };

  if (foods === null)
    return <MySpinner />;

  if (reviews === null)
    return <MySpinner />;

  

  let url = `/login?next=/listfoods/${foods.foodId}`;

  return (
    <>
      <h1 className="text-center text-info mt-2">CHI TIẾT SẢN PHẨM ({foodId})</h1>

      <div className={styles.container}>

        <img className={styles.image}
          src={foods.imgfood}
          alt={foods.name} />
        <div className={styles.details}>
          <div className={styles.header}>
            <span className={styles.name}>{foods.name}</span>
            <Heart isClick={isclick} onClick={() => setClick(!isclick)} />
          </div>

          <span>Loại: {foods.foodType}</span>

          <div className={styles.origin}>
            <span>
              <strong>{foods.status}</strong>
            </span>
          </div>

          <div className={styles.price}>
            <span>{foods.price}</span>
          </div>
          <button onClick={() => order(foods)}>Add to Cart</button>
        </div>
      </div>
      <hr/>

      <img style={{height:'80px',width:'80px'}} src={foods.storeId.imgfoodstore} />
      <h3>{foods.storeId.name}</h3>
      
      <hr/>
      {user === null ? <p>Vui lòng <Link to={url} style={{ color: 'blue' }}> đăng nhập</Link> để bình luận!</p> : <>
        {err === null ? "" : <Alert> {err}</Alert>}
        <Form.Control ref={cont} as="textarea" value={content} onChange={e => setContent(e.target.value)} rows={3} placeholder="Nội dung bình luận..." required />
        <p></p>
        <input
          type="number"
          style={{ width: '150px', border: '0.2px groove', boxShadow: '0 30px 40px rgba(0,0,0,.1)', borderRadius: '5px', textAlign: 'center' }} onBlur={handleBlur} value={rate} max="10" min="0" onChange={e => setRate(e.target.value)} placeholder="rating: ?/10" required />
        <p></p>
        <Button variant="info" onClick={addReview} className="mt-2 mb-2">Bình luận</Button>
      </>}

      <hr />
      {records === null ? <MySpinner /> : <>
        <ListGroup >
          {records.map(c => <ListGroup.Item key={c.reviewId}>
            <div className={styles.comment}>
              <div className={styles.commentAuthor}>
                <Image src={c.userId.avatar} alt={c.userId.firstName}></Image>
                <span>{c.userId.firstName}: </span>
                <p style={{ color: "gray", fontSize: '12px' }}><Moment fromNow>{c.reviewDate}</Moment></p>
              </div>

              <div className={styles.commentContent}>
                {c.comment}
              </div>

              <div className={styles.commentRating}>
                rating: {c.rating}/10
              </div>

              <div>
              
              </div>
            </div>
          </ListGroup.Item>)}
        </ListGroup>
        <nav>
                    <ul className="pagination">
                        <li className="page-item">
                            <a href="#" className="page-link" onClick={preCommentPage}>Prev</a>
                        </li>

                        {numbers.map((n, i) => (
                            <li className={`page-item ${currentReviewPage === n ? 'active' :''}` } key={i}>
                            <a href="#" className="page-link" onClick={()=>changeCommentPerPage(n)}>
                                {n}
                            </a>
                        </li>
                        ))}

                         <li className="page-item">
                            <a href="#" className="page-link" onClick={nextCommentPage}>Next</a>
                        </li>
                    </ul>
                </nav>
      </>}
    </>
  );
};
export default FoodsDetail;
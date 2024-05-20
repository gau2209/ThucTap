
import { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import Api, { endpoints } from "../configs/Api";
import { MyCartContext } from "../App";
import cookie from "react-cookies"

const ListFoods = () => {
    const [, cartDispatch] = useContext(MyCartContext);
    const [stores, setStores] = useState([]);
    const [foods, setFoods] = useState([]);
    const [searchResult, setSearchResult] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 12;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = foods.slice(firstIndex, lastIndex);
    const npage = Math.ceil(foods.length / recordsPerPage);
    const numbers = [...Array(npage + 1).keys()].slice(1);

    useEffect(() => {
        const loadFoods = async () => {
            try {
                const e = endpoints['foods'];
                const res = await Api.get(e);
                setFoods(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }
        loadFoods();
    }, [foods]);

    useEffect(() => {
        const loadStores = async () => {
            try {
                let e = endpoints['stores'];
                let res = await Api.get(e);
                setStores(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }
        loadStores();
    }, [stores]);

    const prePage = () => {
        if (currentPage !== firstIndex) {
            setCurrentPage(currentPage - 1)
        }

    }

    // pagin
    const changeCPage = (id) => {
        setCurrentPage(id)
    }

    const nextPage = () => {
        if (currentPage !== lastIndex) {
            setCurrentPage(currentPage + 1)
        }
    }

    const order = (food) => {
        cartDispatch({
            type: "inc",
            payload: 1
        });

        let cart = cookie.load("cart") || {};
        if (food.foodId in cart) {
            cart[food.foodId]["quantity"] += 1;
        } else {
            cart[food.foodId] = {
                id: food.foodId,
                name: food.name,
                quantity: 1,
                unitPrice: food.price
            }
        }
        cookie.save("cart", cart);
    }

    const handleSearch = (e) => {
        e.preventDefault();
        const searchKeyword = e.target.kw.value.toLowerCase();
        const searchResults = foods.filter((food) =>
            food.name.toLowerCase().includes(searchKeyword)
        );
        setSearchResult(searchResults);
    };

    return (
        <>
            <div>
                <h1 style={{ color: '#717171', textAlign: 'center' }}>Danh sách món ăn</h1>

                <Form className="filter d-flex " onSubmit={handleSearch}>
                    <Form.Control
                        type="text"
                        placeholder="Nhập món ăn bạn muốn tìm kiếm"
                        name="kw"
                        className="me-2"
                        aria-label="Search"
                    />
                    <Button type='submit'>Search</Button>
                </Form>
            </div>
            <p />
            <nav>
                <ul className="pagination">
                    <li className="page-item">
                        <a href="#" className="page-link" onClick={prePage}>Prev</a>
                    </li>
                    {numbers.map((n, i) => (
                        <li className={`page-item ${currentPage === n ? 'active' : ''}`} key={i}>
                            <a href="#" className="page-link" onClick={() => changeCPage(n)}>
                                {n}
                            </a>
                        </li>
                    ))}
                    <li className="page-item">
                        <a href="#" className="page-link" onClick={nextPage}>Next</a>
                    </li>
                </ul>
            </nav>

            <Row>
                {searchResult.length > 0
                    ? searchResult.map((food) => (
                        <Col xs={14} md={4} className="mt-2" key={food.foodId}>
                            <Card style={{ width: '286px', height: '600px', alignItems: 'center' }}>
                                <Card.Img variant="top" src={food.imgfood} style={{ width: '280px', height: '286px' }} />
                                <Card.Body style={{ textAlign: 'left' }}>
                                    <Card.Title style={{ textAlign: 'center' }}>{food.name}</Card.Title>
                                    <Card.Text style={{ textAlign: 'center', fontSize: '16px', fontWeight: '700', color: '#0288d1' }}>
                                        {food.price}
                                        <span style={{ fontWeight: '400', position: 'relative', top: '-9px', fontSize: '10px', right: '0' }}>đ</span></Card.Text>
                                    <hr />
                                    {stores.filter((store) => store.storeId === food.storeId.storeId).map((store, storeId) => {
                                        return (
                                            <>
                                                <div key={storeId} style={{ textAlign: 'center' }}>
                                                    <Card.Img src={store.imgfoodstore} style={{ width: '100px', height: '90px', borderRadius: '30%', objectFit: 'cover' }} />
                                                    <Card.Title style={{ textAlign: 'center' }}>{store.name}</Card.Title>
                                                </div>
                                            </>
                                        )
                                    })}
                                    <hr />
                                    <div style={{ textAlign: 'center' }}>
                                        <Button variant="success" onClick={() => order(food)}>Đặt món</Button>
                                        <Link to={`/listfoods/${food.foodId}`} className="btn btn-info" style={{ marginLeft: '50px' }} variant="primary">Xem chi tiết</Link>
                                    </div>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))
                    : records.map((food) => {
                        return <Col xs={14} md={4} className="mt-2" key={food.foodId}>
                            <Card style={{ width: '286px', height: '600px', alignItems: 'center' }}>
                                <Card.Img variant="top" src={food.imgfood} style={{ width: '280px', height: '286px' }} />
                                <Card.Body style={{ textAlign: 'left' }}>
                                    <Card.Title style={{ textAlign: 'center' }}>{food.name}</Card.Title>
                                    <Card.Text style={{ textAlign: 'center', fontSize: '16px', fontWeight: '700', color: '#0288d1' }}>
                                        {food.price}
                                        <span style={{ fontWeight: '400', position: 'relative', top: '-9px', fontSize: '10px', right: '0' }}>đ</span></Card.Text>
                                    <hr />
                                    {stores.filter((store) => store.storeId === food.storeId.storeId).map((store, storeId) => {
                                        return (
                                            <>
                                                <div key={storeId} style={{ textAlign: 'center' }}>
                                                    <Card.Img src={store.imgfoodstore} style={{ width: '100px', height: '90px', borderRadius: '30%', objectFit: 'cover' }} />
                                                    <Card.Title style={{ textAlign: 'center' }}>{store.name}</Card.Title>
                                                </div>
                                            </>
                                        )
                                    })}
                                    <hr />
                                    <div style={{ textAlign: 'center' }}>
                                        <Button variant="success" onClick={() => order(food)}>Đặt món</Button>
                                        <Link to={`/listfoods/${food.foodId}`} className="btn btn-info" style={{ marginLeft: '50px' }} variant="primary">Xem chi tiết</Link>
                                    </div>
                                </Card.Body>
                            </Card>
                        </Col>
                    })
                }
            </Row>
        </>
    )
}
export default ListFoods;

// {stores.map((store, storeId) => {
//     if (store.storeId === food.storeId.storeId)
//
// })}
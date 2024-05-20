import { useContext, useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom"
import Api, { endpoints } from "../configs/Api";
import MySpinner from "../layout/MySpinner";
import { Button, Card, Col, Row } from "react-bootstrap";
import cookie from "react-cookies"
import styles from "./storeDetail.module.css";
import { MyCartContext } from "../App";

const StoreDetail = () => {
    const { storeId } = useParams();
    const [store, setStore] = useState(null)
    const [foods, setFoods] = useState(null)
    const [, cartDispatch] = useContext(MyCartContext);

    useEffect(() => {
        const loadStore = async () => {
            try {
                let { data } = await Api.get(endpoints['details-store'](storeId));
                setStore(data)
            } catch (ex) {
                console.error(ex)
            }
        }
        loadStore();
    }, [])


    useEffect(() => {
        const loadFoods = async () => {
            try {
                let { data } = await Api.get(endpoints['details-store-foods'](storeId));
                setFoods(data);
            }
            catch (ex) {
                console.error(ex);
            }
        };

        loadFoods();
    }, []);

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

    if (store === null)
        return <MySpinner />

    if (foods === null)
        return <MySpinner />


    return (
        <>
            <h1 style={{ textAlign: 'center' }}>{store.name}</h1>
            <div className={styles.wrap}>
                <div className={styles.top}>
                    <div className={styles.topLeft}>
                        <figure>
                            <img style={{ borderRadius: '10%',width:'576px',height:'330px' }} src={store.imgfoodstore} alt={store.name} />
                            <i class="fa fa-fw fa-plate"></i>
                        </figure>
                        <p className={styles.locate}>{store.location}</p>
                    </div>
                    <div className={styles.topRight}>
                    <iframe title="Google map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.6244964665116!2d106.66679857872309!3d10.763395662013432!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f5a94082ddf%3A0x4c7a1fb5117f6c71!2zMjMzIE5nw7QgR2lhIFThu7EsIFBoxrDhu51uZyA0LCBRdeG6rW4gMTAsIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1694196695476!5m2!1svi!2s"
                            width="280px" height="200px" allowFullScreen=""
                            loading="lazy" referrerPolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>

                <div className={styles.bottom}>
                    <Row>
                        {foods.map(f => (
                            <Col xs={12} md={4} className="mt-2" key={f.foodId}>
                                <Card style={{ width: '286px', height: '450px', alignItems: 'center' }}>
                                    <Card.Img variant="top" src={f.imgfood} style={{ width: '280px', height: '286px' }} />
                                    <Card.Body style={{ textAlign: 'left' }}>
                                        <Card.Title>{f.name}</Card.Title>
                                        <Card.Text className={styles.foodPrice}>{f.price}</Card.Text>
                                        <Button variant="success" onClick={() => order(f)}>Đặt món</Button>
                                        <Link to={`/listfoods/${f.foodId}`} className="btn btn-info" style={{ marginRight: "5px" }} variant="primary">
                                            Xem chi tiết
                                        </Link>
                                    </Card.Body>
                                </Card>
                            </Col>
                        ))}
                    </Row>
                </div>
            </div >
        </>
    )
}
export default StoreDetail
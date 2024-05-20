import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Api, { endpoints } from "../configs/Api";
import MySpinner from "../layout/MySpinner";
import Moment from "react-moment";
import { Col, Image, Row, Table } from "react-bootstrap";

const OrderDetail = () => {
    const { orderId } = useParams();
    const [order, setOrder] = useState(null);
    const [OrderDetail, setOrderDetail] = useState(null);

    useEffect(() => {
        const loadOrder = async () => {
            try {
                let { data } = await Api.get(endpoints["order-Id"](orderId));
                setOrder(data);
            }
            catch (ex) {
                console.error(ex);
            }
        };
        loadOrder();
    }, []);

    useEffect(() => {
        const loadOrderDetail = async () => {
            try {
                let { data } = await Api.get(endpoints['order-detail'](orderId));
                setOrderDetail(data);
            }
            catch (ex) {
                console.error(ex);
            }
        };

        loadOrderDetail();
    }, []);

    if (order === null)
        return <MySpinner />

    if (OrderDetail === null)
        return <MySpinner />

    return (
        <>
            <div style={{ display: "flex", flexDirection: "row" }}>
                <div style={{ width: '50%', textAlign: 'center' }}>
                    <h3>Thông tin người đặt hàng</h3>
                    <Image style={{ width: "128px", height: "128px", objectFit: "cover", borderRadius: "50%" }} src={order.userId.avatar} alt={order.userId.username} />
                    <p>Id người đặt hàng: {order.userId.userId}</p>
                    <p>Người đặt hàng: {order.userId.username}</p>
                </div>

                <div style={{ width: '3px', height: '250px', background: "black" }} />

                <div style={{ width: '50%', textAlign: 'center' }}>
                    <h3>Thông tin đơn hàng</h3>
                    <p>Id đơn hàng: {order.orderId}</p>
                    <p>Ngày đặt hàng: <Moment fromNow>{order.orderDate}</Moment> </p>
                    <p> Hình thức: {order.paymentMethod}</p>
                </div>

            </div>
            <hr style={{ color: 'blue', border: '3px solid' }} />
            <div>

                <h1>Thông tin giỏ hàng</h1>

                <Table striped bordered hover style={{ textAlign: 'center' }}>
                    <thead>
                        <tr>
                            <th>Mã giỏ hàng</th>
                            <th>Số lượng</th>
                            <th>Giá tiền</th>
                            <th>Thông tin món</th>
                            <th>Thông tin quán</th>
                        </tr>
                    </thead>
                    <tbody>
                        {OrderDetail.map((ordt) => (
                            <tr key={ordt.id}>
                                <td>{ordt.id}</td>
                                <td>{ordt.num}</td>
                                <td>{ordt.unitPrice}</td>
                                <td><Image style={{ width: "50px", height: "50px", objectFit: "cover", borderRadius: "50%" }}
                                    src={ordt.foodId.imgfood} alt={ordt.foodId.name} /> id: {ordt.foodId.foodId}
                                    <p>name: {ordt.foodId.name}</p></td>
                                <td><Image style={{ width: "50px", height: "50px", objectFit: "cover", borderRadius: "50%" }}
                                    src={ordt.foodId.storeId.imgfoodstore} alt={ordt.foodId.storeId.name} /> id: {ordt.foodId.storeId.storeId}
                                    <p>name: {ordt.foodId.storeId.name}</p></td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>


        </>
    )
}
export default OrderDetail
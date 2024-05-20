import { useEffect, useState } from "react";
import Api, { endpoints } from "../configs/Api";
import { Button, Card, Col, Row, Table } from "react-bootstrap";
import Moment from "react-moment";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'

const Order = () => {
    const [orderId, setOrders] = useState([]);
    const [orders, setOrder] = useState([])
    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 10;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = orders.slice(firstIndex, lastIndex);
    const npage = Math.ceil(orders.length / recordsPerPage);
    const numbers = [...Array(npage + 1).keys()].slice(1);

    useEffect(() => {
        const loadOrders = async () => {
            try {
                const e = endpoints['order'];
                const res = await Api.get(e);
                setOrder(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }
        loadOrders();
    }, []);

    const prePage = () => {
        if(currentPage!== firstIndex){
            setCurrentPage(currentPage -1 )
        }

    }

    const changeCPage = (id) => {
 setCurrentPage(id)
    }

    const nextPage = () =>{
        if(currentPage!== lastIndex){
            setCurrentPage(currentPage+1)
        }
    }

    let url = `/order/${orderId}`
    return (
        <>
            <input
                value={orderId}
                onChange={(e) => setOrders(e.target.value)} placeholder="Enter Order Id" />
            <Button><Link to={url}>Tìm</Link></Button>

            <hr />
            <div>
                <h1>Thông tin đơn hàng</h1>

                <Table striped bordered hover style={{ textAlign: 'center' }}>
                    <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Ngày đặt</th>
                            <th>Người đặt</th>
                        </tr>
                    </thead>
                    <tbody>
                        {records.map((o) => (
                            <tr key={o.orderId}>
                                <td>{o.orderId}</td>
                                <td><Moment fromNow>{o.orderDate}</Moment></td>
                                <td>{o.userId.username} <Link style={{ color: 'blue', float: 'right' }} to={`/order/${o.orderId}`}>Xem chi tiết</Link></td>
                            </tr>
                        ))}
                    </tbody>
                </Table>

                <nav>
                    <ul className="pagination">
                        <li className="page-item">
                            <a href="#" className="page-link" onClick={prePage}>Prev</a>
                        </li>
                        {numbers.map((n, i) => (
                            <li className={`page-item ${currentPage === n? 'active' :''}` } key={i}>
                            <a href="#" className="page-link" onClick={()=>changeCPage(n)}>
                                {n}
                            </a>
                        </li>
                        ))}
                         <li className="page-item">
                            <a href="#" className="page-link" onClick={nextPage}>Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </>
    )


}
export default Order


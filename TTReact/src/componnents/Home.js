import { useEffect, useState } from "react";
import Api, { endpoints } from "../configs/Api";
import MySpinner from "../layout/MySpinner";
import { Button, Card, Col, Row } from "react-bootstrap";
import { Link, useSearchParams } from "react-router-dom";

const Home = () => {

    const [stores, setStores] = useState([]);
    const [q] = useSearchParams();
    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 9;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = stores.slice(firstIndex, lastIndex);
    const npage = Math.ceil(stores.length / recordsPerPage);
    const numbers = [...Array(npage + 1).keys()].slice(1);

    useEffect(() => {
        const loadStores = async () => {
            try {
                let e = endpoints['stores'];

                let kw = q.get("kw");
                if (kw !== null)
                    e = `${e}?kw=${kw}`;
                let res = await Api.get(e);
                setStores(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }
        loadStores();
    }, [q]);

    const prePage = () => {
        if (currentPage !== firstIndex) {
            setCurrentPage(currentPage - 1)
        }
    }

    const changeCPage = (id) => {
        setCurrentPage(id)
    }

    const nextPage = () => {
        if (currentPage !== lastIndex) {
            setCurrentPage(currentPage + 1)
        }
    }

    if (stores === null)
        return <MySpinner />

    return (
        <>
            <h1 className="text-center text-info">Trang chủ</h1>
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
                {records.map(s => {
                    let url = `/liststores/${s.storeId}`
                    return <Col key={s.storeId} xs={14} md={4} className="mt-2"><Card style={{ width: '286px', height: '530px', alignItems: 'center' }}>
                        <Card.Img variant="top" src={s.imgfoodstore} style={{ width: '280px', height: '182px' }} />
                        <Card.Body>
                            <Card.Title style={{ textAlign: 'center' }}>{s.name}</Card.Title>
                            <Card.Text>{s.location}</Card.Text>
                            <iframe title="Google map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.6244964665116!2d106.66679857872309!3d10.763395662013432!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f5a94082ddf%3A0x4c7a1fb5117f6c71!2zMjMzIE5nw7QgR2lhIFThu7EsIFBoxrDhu51uZyA0LCBRdeG6rW4gMTAsIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1694196695476!5m2!1svi!2s"
                                width="280px" height="200px" allowFullScreen=""
                                loading="lazy" referrerPolicy="no-referrer-when-downgrade"></iframe>
                            <Button style={{ marginLeft: '120px' }} variant="success"><Link to={url}>Xem</Link></Button>
                        </Card.Body>
                    </Card>
                    </Col>
                })}
            </Row>
        </>
    )
}

export default Home
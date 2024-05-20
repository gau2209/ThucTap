import { useEffect, useState } from "react";
import Api, { endpoints } from "../configs/Api";
import MySpinner from "../layout/MySpinner";
import { Button, Card, Col, Row } from "react-bootstrap";
import { Link, useSearchParams } from "react-router-dom";

const ListStores = () => {
    const [stores, setStores] = useState([]);
    const [foods, setFoods] = useState(null)
    const [q] = useSearchParams();
    

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

    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 9;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = stores.slice(firstIndex, lastIndex);
    const npage = Math.ceil(stores.length / recordsPerPage);
    const numbers = [...Array(npage + 1).keys()].slice(1);

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

    useEffect(() => {
        const loadFoods = async () => {
            try {
                let { data } = await Api.get(endpoints['foods']) ;
                setFoods(data);
            }
            catch (ex) {
                console.error(ex);
            }
        };

        loadFoods();
    }, [q]);

    if (stores === null)
        return <MySpinner />

    if (foods === null)
        return <MySpinner />


    return (
        <>
            <h1 style={{ color: '#717171', textAlign: 'center' }}>Danh sách cửa hàng</h1>
            <nav>
                    <ul className="pagination">
                        <li className="page-item">
                            <a href="#" className="page-link" onClick={prePage}>Prev</a>
                        </li>
                        {numbers.map((n, i) => (
                            <li className={`page-item ${currentPage === n ? 'active' :''}` } key={i}>
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

            <Row>
                {records.map((s, storeId) => {
                    let url = `/liststores/${s.storeId}`
                    return <Col key={storeId} xs={14} md={4} className="mt-2"><Card style={{ width: '286px', height: '520px', alignItems: 'center' }}>
                        <Card.Img variant="top" src={s.imgfoodstore} style={{ width: '280px', height: '280px' }} />
                        <Card.Body>
                            <Card.Title style={{textAlign:'center'}}>{s.name}</Card.Title>
                            {foods.filter((f) => f.storeId.storeId === s.storeId).slice(0,3).map((f,foodId) => (
                                    <Card.Text key={foodId}>+ {f.name} - {f.price} VND</Card.Text>    
                                ))}
                                            <div style={{textAlign:'center'}}>
                            <Button variant="success" style={{ margin:'center' }}> <Link to={url}>Xem</Link></Button>
                            </div>
                        </Card.Body>
                    </Card>
                    </Col>
                })}
            </Row>
        </>
    )
}
export default ListStores
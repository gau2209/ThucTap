import { useContext, useState } from "react";
import { Badge, Button, Col, Container, Form, Image, Nav, NavDropdown, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyCartContext, MyUserContext } from "../App";


const Header = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [cartCounter] = useContext(MyCartContext);
  const [kw, setKw] = useState("");
  const nav = useNavigate();
  const search = (evt) => {
    evt.preventDefault();
    nav(`/?kw=${kw}`)

  }

  const logout = () => {
    dispatch({
      "type": "logout"
    })
    nav(`/login`)
  }

  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand >
            <div className="text-container">
              <span style={{ '--color': '#d2e00b', '--delay': '0s' }}>M</span>
              <span style={{ '--color': '#d2e00b', '--delay': '0s' }}>Y</span>
              <span style={{ '--color': '#84BCDA', '--delay': '0s' }}>F</span>
              <span style={{ '--color': '#84BCDA', '--delay': '0s' }}>O</span>
              <span style={{ '--color': '#84BCDA', '--delay': '0s' }}>O</span>
              <span style={{ '--color': '#84BCDA', '--delay': '0s' }}>D</span>
              <span style={{ '--color': '#DA4167', '--delay': '0s' }}>W</span>
              <span style={{ '--color': '#DA4167', '--delay': '0s' }}>E</span>
              <span style={{ '--color': '#DA4167', '--delay': '0s' }}>D</span>
              <span style={{ '--color': '#c589e8', '--delay': '0s' }}>!</span>
            </div>
          </Navbar.Brand>
          <Nav>
            <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/">Trang chủ</Link>
            <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/liststores">Cửa hàng</Link>
            <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/listfoods">Món ăn</Link>


            {user === null ? <>
              <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/login">Đăng nhập</Link>
              <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/register">Đăng ký KH</Link>
              <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/registerUserStore">Đăng ký CH</Link>
            </> : <>
              {user.role === "ROLE_STORE" ?
                <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/addFood">Đăng sản phẩm</Link> :
                <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/register-store">Đăng ký Store</Link>}

              {user.role === "ROLE_ADMIN" ?
                <Link style={{ color: 'white', marginTop: '10px' }} className="nav-link " to="/order">Order</Link> : null}

              <NavDropdown
                title={<Image title="avatar" style={{ width: '45px', height: '45px', borderRadius: '50%', objectFit: 'cover', marginLeft: '15px', border: '2px solid green' }} src={user.avatar}></Image>}
              >
                <NavDropdown.Item>
                  <Link to={`user/${user.userId}`} className="nav-link" style={{ color: 'white', textAlign: 'center' }}> Profile</Link>
                </NavDropdown.Item>

                <NavDropdown.Item style={{ color: 'white', textAlign: 'center' }}>
                  <Link to={`/liststores/${user.storeId.storeId}`} className="nav-link" style={{ color: 'white', textAlign: 'center' }}>Your Store </Link>
                </NavDropdown.Item>

                <NavDropdown.Item style={{ color: 'white', textAlign: 'center' }} onClick={logout}>
                  Sign Out
                  <i className='fas fa-sign-out-alt'></i>
                </NavDropdown.Item>


              </NavDropdown>
            </>}

          </Nav>
          <Form onSubmit={search} inline="true">
            <Row>
              <Col xs="auto">
                <Link style={{ marginLeft: '150px', marginTop: '10px' }} className="nav-link text-danger" to="/cart">&#128722;<Badge bg="danger">{cartCounter}</Badge></Link>
              </Col>
              <Col xs="auto">
                <Form.Control
                  type="text"
                  value={kw}
                  onChange={e => setKw(e.target.value)}
                  placeholder="Enter name's store..." name="kw"
                  className=" mr-sm-2"
                />
              </Col>

              <Col xs="auto">
                <Button type="submit">Tìm</Button>
              </Col>

            </Row>

          </Form>
        </Container>
      </Navbar >
      <br />
    </>

  )
}

export default Header;
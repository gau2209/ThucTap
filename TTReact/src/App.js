import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./componnents/Home";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container } from "react-bootstrap";
import ListStores from "./componnents/ListStores";
import ListFoods from "./componnents/ListFoods";
import Login from "./componnents/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies";
import Register from "./componnents/Register";
import MyCartReducer from "./reducers/MyCartReducer";
import Cart from "./componnents/Cart";
import FoodsDetail from "./componnents/FoodsDetail";
import "./App.css";
import Resetpw from "./componnents/resetPassword";
import AddFood from "./componnents/addFood";
import RegisterStores from "./componnents/RegisterStore";
import UserDetail from "./componnents/userDetail";
import StoreDetail from "./componnents/StoreDetail";
import RegisterUserStore from "./componnents/rgtUserStore";
import Order from "./Order/Order";
import OrderDetail from "./Order/oderDetails";


export const MyUserContext = createContext();
export const MyCartContext = createContext();

const countCart = () => {
  let cart = cookie.load("cart") || null;
  if (cart !== null)
    return Object.values(cart).reduce(
      (init, current) => init + current["quantity"],
      0
    );
  return 0;
};

const App = () => {
  const [user, dispatch] = useReducer(
    MyUserReducer,
    cookie.load("user") || null
  );
  const [cartCounter, cartDispatch] = useReducer(MyCartReducer, countCart());

  return (
    <div className="containerdiv">
      <MyUserContext.Provider value={[user, dispatch]}>
        <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
          <BrowserRouter>
            <Header />
            <div className="wrapper" style={{paddingBottom:'30px'}}>
              <Container>
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/liststores" element={<ListStores />} />
                  <Route path="/listfoods" element={<ListFoods />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/register" element={<Register />} />
                  <Route path="/registerUserStore" element={<RegisterUserStore />} />
                  <Route path="/cart" element={<Cart />} />
                  <Route path="/listfoods/:foodId" element={<FoodsDetail />} />
                  <Route path="/reset-password" element={<Resetpw />} />
                  <Route path="/addFood" element={<AddFood />} />
                  <Route path="/register-store" element={<RegisterStores/>}/>
                  <Route path="/user/:userId" element={<UserDetail/>} />
                  <Route path="/liststores/:storeId" element={<StoreDetail/>} />
                  <Route path="/order" element={<Order/>}/>
                  <Route path="/order/:orderId" element={<OrderDetail/>}/>
                </Routes>
              </Container>
            </div>
            <Footer className="footerapp" />
          </BrowserRouter>
        </MyCartContext.Provider>
      </MyUserContext.Provider>
    </div>
  );
};
export default App;

import { useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Api, { endpoints } from "../configs/Api";
import { useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";

const RegisterUserStore = () => {
    const [user, setUser] = useState({
        "username": "",
        "password": "",
        "firstName": "",
        "lastName": "",
        "confirmPassword": "",
        "role": "ROLE_STORE",
        "email": "",
        "phone": ""
      });
    
      const avatar = useRef();
      const [err, setErr] = useState(null);
      const [loading, setLoading] = useState(false);
      const nav = useNavigate();
    
      const register = (evt) => {
        evt.preventDefault();
    
        const process = async () => {
          let form = new FormData();
    
          for (let field in user)
            if (field !== "confirmPassword")
              form.append(field, user[field]);
    
          form.append("avatar", avatar.current.files[0]);
    
          setLoading(true)
          let res = await Api.post(endpoints['registerUserStore'], form);
          if (res.status === 201) {
            nav("/register-store");
          } else
            setErr("Lỗi hệ thống");
        }
    
        if (user.password === user.confirmPassword)
          process();
        else {
          setErr("Mật khẩu không khớp");
        }
      }
    
      const change = (evt, field) => {
        setUser(current => {
          return { ...current, [field]: evt.target.value }
        })
      }
    
      return <>
        <h1 className="text-center text-info mt-2" >Đăng ký Cửa Hàng</h1>
        {err === null ? "" : <Alert variant="danger">{err}</Alert>}
    
        <Form >
          <Form.Group className="mb-3" >
            <Form.Label>First Name</Form.Label>
            <Form.Control type="text" onChange={(e) => change(e, "firstName")} placeholder="First Name" required />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>Last Name</Form.Label>
            <Form.Control type="text" onChange={(e) => change(e, "lastName")} placeholder="Last Name" required />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>username</Form.Label>
            <Form.Control value={user.username} onChange={(e) => change(e, "username")} type="text" placeholder="username..." required />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>password</Form.Label>
            <Form.Control value={user.password} onChange={(e) => change(e, "password")} type="password" placeholder="password..." required />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control value={user.confirmPassword} onChange={(e) => change(e, "confirmPassword")} type="password" placeholder="ConfirmPassword" required />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" onChange={(e) => change(e, "email")} placeholder="Email" />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>Phone</Form.Label>
            <Form.Control type="text" onChange={(e) => change(e, "phone")} placeholder="Phone" />
          </Form.Group>
          <Form.Group className="mb-3" >
            <Form.Label>Avatar</Form.Label>
            <Form.Control type="file" ref={avatar} />
          </Form.Group>
          <Form.Group className="mb-3">
            {loading === true ? <MySpinner /> : <Button variant="info" type="submit" onClick={register}>Đăng ký</Button>}
          </Form.Group>
        </Form>
      </>
}
export default RegisterUserStore
import { useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Api, { endpoints } from "../configs/Api";
import { useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";

const RegisterStores = ()=>{
    const [store, setStore] = useState({
        "name": "",
        "location": "",
    });
    const file = useRef();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const [err,setErr] = useState(null)

    const handleAddStore = (evt) => {
        evt.preventDefault();
        const process = async () => {
            try {
                let form = new FormData();
                for (let field in store)
                    form.append(field, store[field]);
                form.append("file", file.current.files[0]);

                setLoading(true)
                let res = await Api.post(endpoints['create-store'], form);
                if (res.status === 201) {
                    nav("/login");
                }
                else{
                    setErr("Lỗi hệ thống")
                }
                
            } catch (err) {
                console.log(err);
            }
        }
        process();
    }

    const change = (evt, field) => {
        setStore(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    return <>
        <h1 className="text-center text-info mt-2" >Tạo cửa hàng</h1>
        {err === null ? "" : <Alert>{err}</Alert>}

        <Form>
            <Form.Group className="mb-3" >
                <Form.Label>Name</Form.Label>
                <Form.Control value={store.name} type="text" onChange={(e) => change(e, "name")} placeholder="Food Name" required />
            </Form.Group>

            <Form.Group className="mb-3" >
                <Form.Label>Địa chỉ</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "location")} placeholder="location" required />
            </Form.Group>

            <Form.Group className="mb-3" >
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={file} />
            </Form.Group>

            <Form.Group className="mb-3">
            {loading === true ? <MySpinner /> :<Button variant="info" type="submit" onClick={handleAddStore}>Tạo cửa hàng</Button>}
            </Form.Group>
        </Form>
    </>
}
export default RegisterStores;
  
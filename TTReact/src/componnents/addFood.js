
import { useEffect, useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Api, { authApi, endpoints } from "../configs/Api";
import { useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";
function AddFood() {
    const [food, setFood] = useState({
        "name": "",
        "price": "",
        "storeId": "",
        "status": "",
        "foodType": "Đồ ăn",
    });
    const file = useRef();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const [err,setErr] = useState(null)
    const [user, setUser] = useState(null)
    

    useEffect(() => {
        const loadUser = async () => {
            try {
                let { data } = await authApi().get(endpoints['current-user']);
                setUser(data)
                setFood((prevFood) => ({...prevFood, storeId: data.storeId.storeId}))
            }
            catch (ex) {
                console.error(ex)
            }
        }
        loadUser()
    }, [])

    

    const handleAddFood = (evt) => {
        evt.preventDefault();
        const process = async () => {
            try {
                let form = new FormData();
                for (let field in food)
                    form.append(field, food[field]);
                form.append("file", file.current.files[0]);

                setLoading(true)
                let res = await Api.post(endpoints['createFoods'],form);

                if (res.status === 201) {
                    nav("/login");
                }
                else{
                    setErr("Lỗi hệ thống")
                }

            } catch (err) {
                console.log(err);
            }finally{
                setLoading(false);
            }
        }
        process();
        
    }
        
    const change = (evt, field) => {
        setFood(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    if(user===null)
        return <MySpinner/>


    return <>
        <h1 className="text-center text-info mt-2" >thêm món ăn</h1>
        {err === null ? "" : <Alert>{err}</Alert>}
        
        <Form>
            <Form.Group className="mb-3" >
                <Form.Label>Name</Form.Label>
                <Form.Control value={food.name} type="text" onChange={(e) => change(e, "name")} placeholder="Tên món ăn" required />
            </Form.Group>

            <Form.Group className="mb-3" >
                <Form.Label>Price</Form.Label>
                <Form.Control type="number" onChange={(e) => change(e, "price")} placeholder="Giá cả" required />
            </Form.Group>
            
            <Form.Group className="mb-3" >
                <Form.Label>Status</Form.Label>
                <Form.Control onChange={(e) => change(e, "status")} placeholder="Tình trạng"/>
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Type</Form.Label>
                <Form.Control as="select"  onChange={(e) => change(e, "foodType")}  placeholder="Loại...">
                    <option value="Đồ ăn">Đồ ăn</option>
                    <option value="Đồ uống">Đồ uống</option>
                </Form.Control>
            </Form.Group>

            <Form.Group className="mb-3" >
                <Form.Label>Food Image</Form.Label>
                <Form.Control type="file" ref={file} />
            </Form.Group>

            <Form.Group className="mb-3">
            {loading === true ? <MySpinner /> : <Button variant="info" type="submit" onClick={handleAddFood}>Đăng món ăn</Button>}
            </Form.Group>
        </Form>
        
    </>
    
}
export default AddFood
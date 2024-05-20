import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import MySpinner from "../layout/MySpinner";
import { authApi, endpoints } from "../configs/Api";
import StyleU from "./userDetail.module.css"

const UserDetail = () => {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const loadUser = async () => {
            try {
                let { data } = await authApi().get(endpoints['current-user']);
                setUser(data)
            }
            catch (ex) {
                console.error(ex)
            }
        }
        loadUser()
    }, [])

    if (user === null)
        return <MySpinner />

    let urlmail = `mailto:${user.email}`

    return (

        <>

            <h1 style={{ textAlign: 'center' }}>Profile User</h1>
            <div className="row justify-content-around mt-5 user-info">
                <div className="col-12 col-md-3">
                    <figure className="avatar avatar-profile">
                        <img className={StyleU.imagee} src={user.avatar} alt={user.userId} />
                    </figure>
                    <Link style={{ marginLeft: "90px" }} className="btn btn-primary btn-block my-2">
                        Edit Profile
                    </Link>
                </div>

                <div className="col-12 col-md-5">
                    <h4>Full name</h4>
                    <p>{user.firstName} {user.lastName}</p>

                    <h4>Email Address</h4>
                    <p>{user.email}</p>

                    <h4>Phone</h4>
                    <p>{user.phone}</p>

                    <h4>Role</h4>
                    <p>{user.role}</p>

                    <button className={StyleU.button}><a href={urlmail}>Contact</a></button>
                </div>
            </div>
        </>
    )
}
export default UserDetail;

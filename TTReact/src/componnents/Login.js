import styles from './LoginStyle.module.css'
import clsx from 'clsx';
import { useContext, useState } from "react";
import Api, { authApi, endpoints } from "../configs/Api";
import cookie from "react-cookies";
import { MyUserContext } from "../App";
import { Link, Navigate, useSearchParams } from "react-router-dom";
import { Alert } from 'react-bootstrap';

const Login = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();
  const [role, ] = useState();
  const [err, setErr] = useState(null)
  const [q] = useSearchParams();

  const login = (evt) => {
    evt.preventDefault();
    const process = async () => {
      try {
        let res = await Api.post(endpoints['login'], {
          "username": username,
          "role": role,
          "password": password
        });
        cookie.save("token", res.data);

        let { data } = await authApi().get(endpoints['current-user']);
        cookie.save("user", data);
        console.info(data);

        dispatch({
          "type": "login",
          "payload": data
        })
      } catch (err) {
        setErr('INVALID ACCOUNT!  please check again')
      }
    }
    process();
  }

  if (user !== null){ 
    let next = q.get("next") ||"/"
    return <Navigate to={next} />
}

  return (
    <>
      {err === null ? "" : <Alert variant="danger">{err}</Alert>}

      <div className={clsx(styles.loginContainer)}>
        <h2 className={styles.h2Text}>Login</h2>
        <form onSubmit={login}>
          <input
            className={clsx(styles.inpu)}
            type='text'
            placeholder='Enter Username...'
            value={username}
            onChange={e => setUsername(e.target.value)}
            required />

          <input
            value={password}
            className={clsx(styles.inpu)}
            type="password"
            placeholder='Enter password...'
            onChange={e => setPassword(e.target.value)}
            required />
          <hr style={{ color: 'black', border: '1px solid' }} />
          <div className={styles.forget}>
            <label htmlFor=""><input type='checkbox' />Remember <Link to={"/reset-password"}>Forgot password</Link></label>
          </div>
          <button onClick={login} className={clsx(styles.butSubmit)} type='submit' >Login</button>
          <div className={styles.register}>
            <p>Don't have account?<Link to={"/register"}>Sign Up</Link></p>
          </div>
        </form>
      </div>

    </>
  )
}
export default Login;

import styles from './resetpw.module.css'
import {useState } from 'react'
import { Alert } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

const Resetpw = () => {
    const [err, setErr] = useState(null)
    const [password, setPassword] = useState('')
    const nav = useNavigate()
    const [confirmPassword, setConfirmPassword] = useState('')

    const Confirmpw = (e) => {
        e.preventDefault();

        if(password === ''){
            setErr('Hãy nhập mật khẩu')
            return;
        }

        if(confirmPassword === '')
        {
            setErr('Hãy xác nhận mật khẩu')
            return;
        }

        if (password !== confirmPassword) {
            setErr('Mật khẩu không khớp')
            return;
        }
        else
            nav('/login')

    }


    return (
        <>
            {err === null ? "" : <Alert variant="danger">{err}</Alert>}

            <div className={styles.containerbody}>
                <div className={styles.card}>
                    <p className={styles.lockicon}><i class="fas fa-lock"></i></p>
                    <h2>Forgot Password?</h2>
                    <p style={{ fontSize: '13px' }}>You can reset your Password here</p>
                    <input type="password"
                        className={styles.passInput}
                        onChange={e => setPassword(e.target.value)}
                        placeholder="Enter your Password..." />

                    <input type="password"
                        className={styles.passInput}
                        onChange={e => setConfirmPassword(e.target.value)}
                        placeholder="Confirm PassWord..." />
                    <button className={styles.button} onClick={Confirmpw}>Reset My Password</button>
                </div>
            </div>
        </>
    )
}
export default Resetpw

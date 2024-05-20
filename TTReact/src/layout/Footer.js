
import styleFooter from './Footer.module.css'

const Footer = () => {
    return (
        <>
            <div style={{ display: 'block', bottom: 0 }}>
                <div className={styleFooter.footer}>
                    <div className={styleFooter.section}>
                        <h2 >Contact Us</h2>
                        <ul style={{ textAlign: 'left' }}>
                            <h5>+ 0934179579</h5>
                            <h5>+ 0934179579</h5>
                            <h5>+ Click to mail us <a className={styleFooter.icon} href="mailto:anhtuanvo2209@gmail.com" style={{ '--color': '#e5f50c' }}>
                                <i className="fa fa-envelope"></i>
                            </a></h5>
                        </ul>
                    </div>

                    <div className={styleFooter.section}>
                        <h2 className={styleFooter.Text}>Follow Us At</h2>
                        <div className={styleFooter.wrapper}>
                            <a className={styleFooter.icon} href="https://www.linkedin.com/" style={{ '--color': '#0072b1' }}>
                                <span className={styleFooter.tooltip}>Linkedin</span>
                                <i className="fa-brands fa-linkedin-in"></i>
                            </a>

                            <a className={styleFooter.icon} href="https://www.instagram.com/" style={{ '--color': '#E1306C' }}>
                                <span className={styleFooter.tooltip}>Instagram</span>
                                <i className="fa-brands fa-instagram"></i>
                            </a>

                            <a className={styleFooter.icon} href="https://www.tiktok.com/" style={{ '--color': '#ff0050' }}>
                                <span className={styleFooter.tooltip}>Tiktok</span>
                                <i className="fa-brands fa-tiktok"></i>
                            </a>

                            <a className={styleFooter.icon} href="https://www.facebook.com/" style={{ '--color': '#fff' }}>
                                <span className={styleFooter.tooltip}>Facebook</span>
                                <i className="fa-brands fa-github"></i>
                            </a>
                        </div>
                    </div>

                    <div className={styleFooter.section}>
                        <h2 className={styleFooter.Text}>Thành viên</h2>
                        <ul style={{ textAlign: 'left' }}>
                            <li style={{ color: 'white' }}><a className={styleFooter.adots} href="https://github.com/nhtuna">2051012128 - Võ Hữu Anh Tuấn</a></li>
                            <li> <a className={styleFooter.adots} href="https://github.com/TangPhucHoangTu"> 2051012128 - Tăng Phúc Hoàng Tú</a></li>
                        </ul>
                    </div>


                </div>
                <div className={styleFooter.info}>
                    <h4 className={styleFooter.hText}>
                        <a className={styleFooter.aText} href="https://www.facebook.com/CallmeGau.2307/">Anh Tuan | </a>
                        FoodWeb &copy; 2023
                        <a className={styleFooter.aText} href="https://www.facebook.com/TPHT230302"> | Hoang Tu</a></h4>
                </div>
            </div>
        </>

    )
}

export default Footer;
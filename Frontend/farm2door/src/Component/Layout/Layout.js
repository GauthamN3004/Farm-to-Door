import Footer from "./Footer/Footer.js";
import Header from "./Header/Header.js";
import Content from "./Content/Content.js"
import Content2 from "./Content/Content2.js"
// import { ToastContainer } from 'react-toastify';
import toast, { Toaster } from 'react-hot-toast';
import { useAuth } from "../../Context/AuthContext.js";
import "./Layout.css"
import UserHeader from "./UserHeader/UserHeader.js";



function Layout({children}) {
    const {isLoggedIn, userData, login, logout} = useAuth();
    return (
        <>
            <div className="layout">
                {isLoggedIn ? <UserHeader /> : <Header />}
                {children}
            </div>
            <Toaster />
            <Footer />
        </>
    );
}


export default Layout;
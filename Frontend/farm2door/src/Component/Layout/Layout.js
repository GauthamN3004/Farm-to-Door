import Footer from "./Footer/Footer.js";
import Header from "./Header/Header.js";
import Content from "./Content/Content.js"
import Content2 from "./Content/Content2.js"
// import { ToastContainer } from 'react-toastify';
import toast, { Toaster } from 'react-hot-toast';
import "./Layout.css"

function Layout({children}) {
    return (
        <>
            <div className="layout">
                <Header />
                {/* <ToastContainer /> */}
                {children}
            </div>
            <Toaster />
            <Footer />
        </>
    );
}


export default Layout;
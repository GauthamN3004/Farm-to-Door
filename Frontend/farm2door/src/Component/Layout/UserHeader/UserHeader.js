import React, { useState } from 'react';
import "./UserHeader.css"
import { Link, NavLink } from "react-router-dom";
import imageLogo from "./logo.png";
import { useAuth } from '../../../Context/AuthContext';
import { useNavigate } from "react-router-dom";

function UserHeader() {
    const [menuOpen, setMenuOpen] = useState(false);
    const {isLoggedIn, userData, login, logout} = useAuth();

    const navigate = useNavigate();

    function handleLogout(){
        logout();
        navigate("/");
    }

    function handleNavToggle() {
        setMenuOpen(!menuOpen);
    }

    return (
        <>
            <nav className="navBar">
                <div className="brandLogo">
                <Link to="/dashboard">
                    <img src={imageLogo} height={80} alt="Logo"></img>
                </Link>
                </div>
                <div className="toggleButton" onClick={() => handleNavToggle()}>
                <span className="bar"></span>
                <span className="bar"></span>
                <span className="bar"></span>
                </div>
                <div className="navBarLinks">
                <ul className={menuOpen ? "open" : ""}>
                    {/* <li>
                    <NavLink to="/about">ABOUT</NavLink>
                    </li> */}
                    {/* <li>
                    <NavLink to="/login">
                        <span className="glyphicon glyphicon-log-in"></span> LOGIN
                    </NavLink>
                    </li> */}
                    <li>
                    <NavLink to="/" onClick={handleLogout}>LOGOUT</NavLink>
                    </li>
                </ul>
                </div>
            </nav>
        </>
    )
}

export default UserHeader;
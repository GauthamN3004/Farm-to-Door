import React, { useState } from 'react';
import "./Header.css"
import { Link, NavLink } from "react-router-dom";
import imageLogo from "./logo.png";

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);

    function handleClick() {
        setMenuOpen(!menuOpen);
        console.log(handleClick);
    }

    return (
        <>
            <nav className="navBar">
                <div className="brandLogo">
                <Link to="/">
                    <img src={imageLogo} height={80} alt="Logo"></img>
                </Link>
                </div>
                <div className="toggleButton" onClick={() => handleClick()}>
                <span className="bar"></span>
                <span className="bar"></span>
                <span className="bar"></span>
                </div>
                <div className="navBarLinks">
                <ul className={menuOpen ? "open" : ""}>
                    <li>
                    <NavLink to="/about">ABOUT</NavLink>
                    </li>
                    <li>
                    <NavLink to="/login">
                        <span className="glyphicon glyphicon-log-in"></span> LOGIN
                    </NavLink>
                    </li>
                    <li>
                    <NavLink to="/register">REGISTER</NavLink>
                    </li>
                </ul>
                </div>
            </nav>
        </>
    )
}

export default Header;
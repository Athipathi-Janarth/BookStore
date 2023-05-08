import logo from "../../../online-website/src/Assets/logonew.png"
import React from "react";
//import {getAllBooks} from "../Utils/ApiCall";
const Header = () => {
    //Books =  getAllBooks();

    return (
        <div className="header">
            <div className="logo-img"><img src={logo} alt="logo"/></div>
            <div className="header-heading"><h1>Book Store</h1><h3>Read books online. From bestsellers to niche books..</h3></div>
        </div>
    )
}
export default Header;
import './App.css';
import Header from "./Components/Header";
import {Link, Route, Routes} from "react-router-dom";
import Home from "./Pages/Home";
import AddBook from "./Pages/AddBook";
import Search from "./Pages/Search";
import React from "react";
import UpdateBook from "./Pages/UpdateBook";

function App() {
  return (
    <div className="App">
                <Header/>
        <div id="large-th">
            <div className="container">
                <div className="choose">
                    <Link to={"/Search"}><i className="fa fa-search" aria-hidden="true"></i></Link>
                    <Link to={"/AddBook"}><i className="fa fa-plus" aria-hidden="true"></i></Link>
                    <Link to={"/"}><i className="fa fa-list" aria-hidden="true"></i></Link>
                </div>
                <Routes>
                    <Route exact path="/" element={<Home/>} />
                    <Route path="/AddBook" element={<AddBook/>} />
                    <Route path="/Search" element={<Search/>} />
                    <Route path="/Book/:bookId" element={<UpdateBook/>} />
                </Routes>
            </div>
        </div>
                {/* Your footer or other common components can go here */}
    </div>
  );
}

export default App;

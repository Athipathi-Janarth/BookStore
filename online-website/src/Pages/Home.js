import React, {useEffect, useState} from "react";
import {getAllBooks} from "../Utils/ApiCalls";
import Book from "../Components/Book";
import {Link} from "react-router-dom";

const Home = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        getList().then(r => console.log());
    }, []);

    async function getList() {
        try {
            const response = await getAllBooks();
            const data = await response.json();
            setBooks(data);
        } catch (error) {
            console.error('Error retrieving books:', error);
        }
    }
    return(
   <div>
            <h1>Your Collection's</h1>
            <br/>
                <div id="list-th">
                    {books.map((book) => (
                        <Link to={`/Book/${book.isbn}`}><Book book={book}></Book></Link>
                    ))}
                </div>
            </div>
)
}
export default Home;
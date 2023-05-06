import {useEffect, useState} from "react";
import {getBook} from "../Utils/ApiCalls";
import Book from "../Components/Book";
import {validateISBN} from "../Utils/CheckISBN"

const Search = () => {
    const [book, setBook] = useState(null);
    const [isbn, setISBN] = useState();

    async function getABook() {
        try {
            const response = await getBook(isbn.toString());
            const data = await response.json();
            setBook(data);
            alert("Book Added to Your Collection");
        } catch (error) {
            console.error('Error retrieving books:', error);
        }
    }
    const handleValidation = () => {
        setBook(null);
        const isValid = validateISBN(isbn);
        if(isValid) {
            getABook();
        }
        else{
            alert("ISBN Number is invalid")
        }
    };
    return(
        <div>
            <div className="searchInputWrapper">
                <input className="searchInput" type="text" placeholder="Please enter the isbn number" value={isbn}
                       onChange={(e) => setISBN(e.target.value)}/>
                <i className="searchInputIcon fa fa-search" onClick={handleValidation}></i>
            </div>
            {book!=null? <Book book={book}></Book>:<div className={"not-Found"}>No Book Found!!!</div>}
        </div>

    )
}
export default Search;
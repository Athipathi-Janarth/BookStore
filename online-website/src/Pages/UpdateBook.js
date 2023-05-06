import BookForm from "../Components/BookForm";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getBook} from "../Utils/ApiCalls";

const UpdateBook = () => {
    const { bookId } = useParams();
    const [book, setBook] = useState(null);

    useEffect(() => {
        fetchBook();
    }, [bookId]);

    async function fetchBook() {
        try {
            const response = await getBook(bookId);
            const data = await response.json();
            setBook(data);
        } catch (error) {
            console.error('Error retrieving book:', error);
        }
    }
    console.log(book)
    return(
        <div>
            <h2>Modify Book</h2>
            {book!=null? <BookForm book={book}/>:null}
        </div>
    )
}
export default UpdateBook;
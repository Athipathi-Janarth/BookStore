import BookForm from "../Components/BookForm";

const AddBook = () => {
    return(
        <div>
            <h2>Add Your Book</h2>
            <BookForm book={null}/>
        </div>
    )
}
export default AddBook;
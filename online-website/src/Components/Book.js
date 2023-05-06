import React from "react";

const Book = (props) => {
    const { book } = props;
    return (
        <div className="book read" key={book.isbn}>
            <div className="cover">
                <span className="rating">{book.rating}</span>
                <img src={book.imageUrl}/>
            </div>
            <div className="description">
                <p className="title">{book.title}<br/>
                    <span className="author">{book.author}</span>
                </p>
            </div>
        </div>
    )
}
export default Book;
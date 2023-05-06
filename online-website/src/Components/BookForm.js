import React, { useState } from 'react';
import {validateISBN} from '../Utils/CheckISBN';
import {deleteBook, updateBook, addBook} from "../Utils/ApiCalls";
import {redirect, useNavigate} from "react-router-dom";



const BookForm = (props) => {
    const [book, setBook] = useState(props.book);
    const navigate = useNavigate()
    const [formData, setFormData] = useState({
        isbn: '',
        title: '',
        author: '',
        num_pages: 0,
        notes: '',
        isread: false,
        rating: 0.0,
        image_url: ''
    });
    const [errors, setErrors] = useState({});

    React.useEffect(() => {
        if (book) {
            setFormData({
                isbn: book.isbn,
                title: book.title,
                author: book.author,
                num_pages: book.numPages,
                notes: book.notes,
                isread: book.read,
                rating: book.rating,
                image_url: book.imageUrl
            });
        }
    }, [book]);
    async function handleCreate() {
        const validationErrors = validateForm();
        if (Object.keys(validationErrors).length === 0) {
            const updatedBook = {
                isbn: formData.isbn,
                title: formData.title,
                author: formData.author,
                numPages: formData.num_pages,
                notes: formData.notes,
                read: formData.isread === "on" ? true : false,
                rating: formData.rating,
                imageUrl: formData.image_url
            };
            try{
                await addBook(updatedBook)
                alert("Book Created Successfully");
                navigate('/')
            }
            catch (error) {
                console.error('Error Creating book:', error);
            }
            console.log('Form is valid:', formData);
        } else {
            setErrors(validationErrors);
        }
    }
    async function handleUpdate() {
        const validationErrors = validateForm();
        if (Object.keys(validationErrors).length === 0) {
            const updatedBook = {
                isbn: formData.isbn,
                title: formData.title,
                author: formData.author,
                numPages: formData.num_pages,
                notes: formData.notes,
                read: formData.isread === "on" ? true : false,
                rating: formData.rating,
                imageUrl: formData.image_url
            };
            console.log(updatedBook)
            try{
                await updateBook(book.isbn.toString(),updatedBook)
                alert("Book Updated Successfully");
                navigate('/')
            }
            catch (error) {
                console.error('Error Updating book:', error);
            }
            console.log('Form is valid:', formData);
        } else {
            setErrors(validationErrors);
        }
    }
    async function handleDelete() {
        try {
            await deleteBook(book.isbn);
            navigate('/')
        } catch (error) {
            console.error('Error deleting book:', error);
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = validateForm();
        if (Object.keys(validationErrors).length === 0) {
            console.log('Form is valid:', formData);
        } else {
            setErrors(validationErrors);
        }
    };

    const validateForm = () => {
        let errors = {};
        if (!formData.isbn || !validateISBN(formData.isbn.toString())) {
            errors.isbn = 'ISBN is invalid';
        }
        if (!formData.title) {
            errors.title = 'Title is required';
        }
        if (!formData.author) {
            errors.author = 'Author is required';
        }
        if (formData.num_pages <= 0) {
            errors.num_pages = 'Number of pages must be greater than 0';
        }
        // Add more validation rules for other fields as needed
        return errors;
    };

    return (
                    <form className="book-form" onSubmit={handleSubmit}>
                    <table className="book-form-table">
                        <tbody>
                        <tr>
                            <td>
                                <label className="form-label">
                                    ISBN:
                                </label>
                            </td>
                            <td>
                                { book!=null ? <input
                                    className="form-input"
                                    type="text"
                                    name="isbn"
                                    value={book.isbn}
                                    disabled={true}
                                />: <input
                                    className="form-input"
                                    type="text"
                                    name="isbn"
                                    value={formData.isbn}
                                    onChange={handleChange}/>
                                    }
                                {errors.isbn && <span className="error">{errors.isbn}</span>}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Title:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-input"
                                    type="text"
                                    name="title"
                                    value={formData.title}
                                    onChange={handleChange}
                                />
                                {errors.title && <span className="error">{errors.title}</span>}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Author:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-input"
                                    type="text"
                                    name="author"
                                    value={formData.author}
                                    onChange={handleChange}
                                />
                                {errors.author && <span className="error">{errors.author}</span>}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Number of Pages:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-input"
                                    type="number"
                                    name="num_pages"
                                    value={formData.num_pages}
                                    onChange={handleChange}
                                />
                                {errors.num_pages && <span className="error">{errors.num_pages}</span>}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Notes:
                                </label>
                            </td>
                            <td>
        <textarea
            className="form-input"
            name="notes"
            value={formData.notes}
            onChange={handleChange}
        />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Is Read:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-checkbox"
                                    type="checkbox"
                                    name="isread"
                                    checked={formData.isread}
                                    onChange={handleChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Rating:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-input"
                                    type="number"
                                    name="rating"
                                    value={formData.rating}
                                    onChange={handleChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className="form-label">
                                    Image URL:
                                </label>
                            </td>
                            <td>
                                <input
                                    className="form-input"
                                    type="text"
                                    name="image_url"
                                    value={formData.image_url}
                                    onChange={handleChange}
                                />
                            </td>
                        </tr>
                        {book != null ?
                            <tr>
                            <td><button className="form-submit" type="button" onClick={handleUpdate}>Submit</button></td>
                            <td><button className="form-delete" type="button" onClick={handleDelete}>Delete</button></td>
                            </tr> : null}
                        </tbody>
                    </table>
                        {
                            book == null ? <button className="form-submit" type="button" onClick={handleCreate}>Submit</button> : null
                        }
                </form>
    )}
export default BookForm;


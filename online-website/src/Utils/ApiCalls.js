const _apiHost = 'http://localhost:8080/v1/books';

async function request(endpoint, method = 'GET', type = 'application/json', body) {
    const options = {
        method,
        headers: {
            'Content-Type': type,
        },
        body: JSON.stringify(body),
    };

    const response = await fetch(_apiHost + endpoint, options);
    return response;
}
export function getAllBooks() {
    return request('/getBooks');
}
export function getBook(isbn) {
    return request('/getbook/' + isbn);
}
export function addBook(book) {
    return request('/addbook', 'POST', 'application/json', book);
}
export function deleteBook(isbn){
    return request('/deletebook/'+ isbn,'DELETE',)
}
export function updateBook(isbn,book){
    return request('/updatebook/'+isbn, 'PUT', 'application/json', book);
}
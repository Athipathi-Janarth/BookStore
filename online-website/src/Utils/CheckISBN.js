export const validateISBN = (isbn) => {
    const regex = /^[0-9]{13}$/;
    if (!regex.test(isbn)) {
        return false;
    }
    const digits = isbn.split('').map(Number);
    const checksum = digits.pop();
    let sum = 0;
    digits.forEach((digit, index) => {
        sum += digit * (index % 2 === 0 ? 1 : 3);
    });
    const calculatedChecksum = (10 - (sum % 10)) % 10;
    return calculatedChecksum === checksum;
};
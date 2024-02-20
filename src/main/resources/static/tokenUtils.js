// tokenUtils.js

// Function to store the JWT token in localStorage
function storeToken(token) {
    localStorage.setItem('jwtToken', token);
}

// Function to retrieve the JWT token from localStorage
function getStoredToken() {
    return localStorage.getItem('jwtToken');
}

// Function to remove the JWT token from localStorage
function removeToken() {
    localStorage.removeItem('jwtToken');
}


// tokenUtils.js

// Function to store the JWT token in local storage
function storeToken(token) {
    localStorage.setItem('jwtToken', token);
}

// Function to retrieve the stored JWT token
function getStoredToken() {
    return localStorage.getItem('jwtToken');
}

// Function to set the Authorization header with the Bearer token
function setAuthorizationHeader() {
    var token = getStoredToken();
    if (token) {
        // Include the token in the Authorization header for all requests
        $.ajaxSetup({
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });
    }
}

// Function to remove the JWT token from local storage and clear the Authorization header
function clearToken() {
    localStorage.removeItem('jwtToken');
    $.ajaxSetup({
        headers: {
            'Authorization': null
        }
    });
}

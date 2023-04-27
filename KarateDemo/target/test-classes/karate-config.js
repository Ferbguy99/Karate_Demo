function() {

    var config = {
        "baseUrl": "https://restful-booker.herokuapp.com",
        "auth": "/auth",
        "bookingPath": "/booking",
        "auth_username": "admin",
        "auth_password": "password123",
        "microsoftHomeUrl": "https://www.microsoft.com",
    };

    karate.configure('readTimeout', 150000);
    return config;
}
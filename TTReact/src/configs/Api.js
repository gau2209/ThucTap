import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/DiaDiemAnUong";
const SERVER = "http://localhost:8080/";

export const endpoints = {
    // === Store ===
    "stores": `${SERVER_CONTEXT}/api/stores/`,
    "details-store": (storeId) => `${SERVER_CONTEXT}/api/stores/${storeId}/`,
    "details-store-foods" :(storeId) => `${SERVER_CONTEXT}/api/stores/${storeId}/foods/`,
    "create-store": `${SERVER_CONTEXT}/api/createStores/`,
    // === Foods ===
    "foods": `${SERVER_CONTEXT}/api/foods/`,
    "createFoods": `${SERVER_CONTEXT}/api/createFoods/`,
    "details": (foodId) => `${SERVER_CONTEXT}/api/foods/${foodId}/`,
    "reviews": (foodId) => `${SERVER_CONTEXT}/api/foods/${foodId}/reviews/`,
    "add-review": `${SERVER_CONTEXT}/api/reviews/`,
    // === Cart ===
    "pay": `${SERVER_CONTEXT}/api/pay/`,
    // === Order ===
    "order": `${SERVER_CONTEXT}/api/order/`,
    "order-Id": (orderId) => `${SERVER_CONTEXT}/api/order/${orderId}/`,
    "order-detail": (orderId) => `${SERVER_CONTEXT}/api/order/${orderId}/orderdetails/`,
    // === User ===
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "registerUserStore": `${SERVER_CONTEXT}/api/usersStore/`,
    "update-user": `${SERVER_CONTEXT}/api/updateUsers/`,
    "details-user":(userId) => `${SERVER_CONTEXT}/api/user/${userId}`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})
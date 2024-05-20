const MyCartReducer = (curentState, action) => {
    switch (action.type) {
        case "inc":
            return curentState + action.payload;
        case "desc":
            return curentState - action.payload;
        case "update":
            return action.payload;
    }

    return curentState;
}
export default MyCartReducer;
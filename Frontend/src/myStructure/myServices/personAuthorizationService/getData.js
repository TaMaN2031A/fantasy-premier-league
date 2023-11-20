export const login = (userName, password) => {
    // Make a call to service
    // to check if given values are correct...
    return new Promise((resolve, reject) => {

        if (password === "p") {
            console.log("in getdata correct")
            resolve("success")
        } else {
            reject("Incorrect password")
            console.log("in getdata not")
        }
    })
}


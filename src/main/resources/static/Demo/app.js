const BASE_URL = "http://localhost:8080/api/auth";

async function register() {

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    try {

        const response = await fetch(`${BASE_URL}/register`, {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if(result.success){

            localStorage.setItem(
                "accessToken",
                result.data.accessToken
            );

            localStorage.setItem(
                "refreshToken",
                result.data.refreshToken
            );

            localStorage.setItem(
                "user",
                JSON.stringify(result.data)
            );

            alert("Registration Successful");

            window.location.href="profile.html";

        }else{
            document.getElementById("msg").innerHTML=result.message;
        }

    } catch(error){
        console.error(error);
    }
}

async function login(){

    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    try {

        const response = await fetch(`${BASE_URL}/login`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        });

        const result = await response.json();

        if(result.success){

            localStorage.setItem(
                "accessToken",
                result.data.accessToken
            );

            localStorage.setItem(
                "refreshToken",
                result.data.refreshToken
            );

            localStorage.setItem(
                "user",
                JSON.stringify(result.data)
            );

            alert("Login Successful");

            window.location.href="profile.html";

        }else{
            document.getElementById("msg").innerHTML=result.message;
        }

    } catch(error){
        console.error(error);
    }
}
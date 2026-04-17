async function handleForm(e) {
    e.preventDefault();  // We need to set up the request ourselves with a body

    let valid = true;
    const sender = document.querySelector("#from");
    const subject = document.querySelector("#subject");
    const body = document.querySelector("#body");

    // Validate input
    if (!sender.value) {
        valid = false;
        sender.nextElementSibling.textContent = "Required";
    } else {
        sender.nextElementSibling.textContent = "";
    }

    if (!subject.value) {
        valid = false;
        subject.nextElementSibling.textContent = "Required";
    } else {
        subject.nextElementSibling.textContent = "";
    }

    if (!body.value) {
        valid = false;
        body.nextElementSibling.textContent = "Required";
    } else {
        body.nextElementSibling.textContent = "";
    }

    if (!valid) {
        console.log("Invalid");
        return;
    }

    // Parse the form info to JSON for the POST request's body
    const json = JSON.stringify({
        "from": sender.value,
        "subject": subject.value,
        "body": body.value
    });

    // Send the email info to backend
    const response = await fetch("http://localhost:8080/ServletFullStack_war_exploded/api/email", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "*/*"
        },
        body: json
    })

    // Notify the user whether it was sent
    document.querySelector("#email-confirm").textContent = (response.ok) ? "Email Sent" : "Email Failed to Send";
}

document.addEventListener("DOMContentLoaded", ()=>{
    document.querySelector("#email-form").addEventListener("submit", (e)=>{handleForm(e);})
})
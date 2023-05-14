function showPopup(message) {
    if(message.length > 0){
        document.getElementById("message").innerText = message;
    }
    document.getElementById("popup").style.display = "block";
}

function hidePopup() {
    document.getElementById("popup").style.display = "none";
}

function confirmLogout() {
    window.location.href = "/";
    hidePopup();
}

// like post
const likeIcon = document.querySelector(".icon-like");

likeIcon.addEventListener("click", handleLikeAction);


function handleLikeAction(e){
    likeIcon.classList.add("scale");
}
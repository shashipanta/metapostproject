function showApprovePopup() {
    document.getElementById("popups").style.display = "block";
}
function declineApprove() {
    document.getElementById("popups").style.display = "none";
}

function sendButtonValue(buttonValue, postId) {
    $.ajax({
        type: "POST",
        url: "/admin/process",
        data: { buttonValue: buttonValue, postId: postId },
        success: function(response) {
            // Handle the response from the server
        },
        error: function(xhr, status, error) {
            // Handle the error if any
        }
    });
    hidePopup();
}
function sendButtonValueReject(buttonValue, postId) {
    $.ajax({
        type: "POST",
        url: "/admin/rejectprocess",
        data: { buttonValue: buttonValue, postId: postId },
        success: function(response) {
            // Handle the response from the server
        },
        error: function(xhr, status, error) {
            // Handle the error if any
        }
    });
}


function showRejectPopup() {
    document.getElementById("Rejectpopups").style.display = "block";
}

function declineReject() {
    document.getElementById("Rejectpopups").style.display = "none";
}

function confirmReject() {
    window.location.href = "/";

    declineApprove();
}

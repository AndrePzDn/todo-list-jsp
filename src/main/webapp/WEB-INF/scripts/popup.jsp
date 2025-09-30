<html>
<style>
    <%@include file="../styles/popup.css"%>
</style>
<script>
    let toastContainer;

    function toast(message) {
        if (document.getElementsByClassName("toast-container").length === 0) {
            toastContainer = document.createElement("div")
            toastContainer.classList.add("toast-container")
            document.body.appendChild(toastContainer)
        }

        const textTag = document.createElement("p")
        textTag.classList.add("toast-notification")
        textTag.innerHTML = message
        toastContainer.prepend(textTag)
        textTag.addEventListener("click", function () {
            textTag.remove();
        })

        setTimeout(() => {
            textTag.remove();
        }, 3000)
    }

    <%
        if (session.getAttribute("toastMessage") != null) {
        %>
    toast("<%=session.getAttribute("toastMessage")%>")
    <%
        application.removeAttribute("toastMessage");
    }
%>
</script>
</html>
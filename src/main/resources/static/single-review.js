const tagDiv = document.querySelector(".tag-anchor");
const reviewIdElement = document.querySelector(".tag-anchor__review-id");
const newTagNameInput = document.querySelector(".tag-adder__name");
const tagAdderSubmitButton = document.querySelector(".tag-adder__submit");

tagAdderSubmitButton.addEventListener("click", (event) => {
    addTagToReview(reviewIdElement.value, newTagNameInput.value);
    event.preventDefault();
})

function addTagToReview(reviewId, tagName) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            // element.innerText = xhttp.responseText;
            const tag = JSON.parse(xhttp.responseText);

            addTagToPage(tag, tagDiv);
        }
    }

    xhttp.open("POST", "http://localhost:8080/tags/add-tag-to-review/" + reviewId + "/" + tagName, true)
    xhttp.send()
}

function deleteTagFromReview(reviewId, tagId) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            // element.innerText = xhttp.responseText;
            const tags = JSON.parse(xhttp.responseText);

            redrawTags(tags, tagDiv);
        }
    }

    xhttp.open("DELETE", "http://localhost:8080/tags/" + tagId + "/" + reviewId, true)
    xhttp.send()
}

function getTagInfo(element, reviewId) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            // element.innerText = xhttp.responseText;
            const tags = JSON.parse(xhttp.responseText);
            console.log(tags);
            addTagsToPage(tags, element);
        }
    }

    xhttp.open("GET", "http://localhost:8080/tags/tags-for-review/" + reviewId, true)
    xhttp.send()
}

function addTagsToPage(tags, element) {
    for (let i = 0; i < tags.length; i++) {
        let tag = tags[i];
        createTagDiv(tag, element);
    }
}
function redrawTags(tags, element) {
    while (element.hasChildNodes()) {
        element.removeChild(element.childNodes[0]);
    }

    for (let i = 0; i < tags.length; i++) {
        let tag = tags[i];
        createTagDiv(tag, element);
    }
}

function createTagDiv(tag, element) {

    let tagDiv = document.createElement("div");
    tagDiv.innerText = tag.name;

    tagDiv.className = "tag-div"
    let deleteButton = document.createElement("button");
    deleteButton.innerText = "DELETE";
    deleteButton.addEventListener("click", (event) => {
        deleteTagFromReview(reviewIdElement.value, tag.id)
        event.preventDefault();
    })
    tagDiv.appendChild(deleteButton);
    element.appendChild(tagDiv);
}

function addTagToPage(tag, element) {
    createTagDiv(tag, element)
}
getTagInfo(tagDiv, reviewIdElement.value);
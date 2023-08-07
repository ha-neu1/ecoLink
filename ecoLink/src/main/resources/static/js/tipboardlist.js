var ChangeValue = function(value) {
    window.location.href = 'tipboardlist?selectValue=' + value;
};

var OptionValue = function(value) {
    console.log('Selected value:', value);
};

var submitForm = function() {
    var selectElement = document.querySelector("#search_form select[name='item']");
    var selectedValue = selectElement.value;

    var inputValue = document.querySelector("#search_form input[name='word']").value;

    var pageUrl = "tipboardsearch?page=1&item=" + encodeURIComponent(selectedValue) + "&word=" + encodeURIComponent(inputValue);
    
    
    if (selectedValue !== "undefined" && inputValue.trim() !== "") {
        window.location.href = pageUrl;
    } else {
        alert("검색 옵션을 선택하고 검색 키워드를 입력하십시오.");
    }
};
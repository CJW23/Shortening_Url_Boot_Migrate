let urlData = [];

/**
 * url URL 등록 요청
 */
function requestGuestCreateUrl() {
    let url = $('#enter_url').val();
    //URL 입력이 없을때.
    if (url === "") {
        return;
    }

    $.ajax({
        type: 'post',
        url: '/guest/create',
        dataType: 'json',
        data: {originalUrl: url},
        success: function (data) {
            guestCreateResponse(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

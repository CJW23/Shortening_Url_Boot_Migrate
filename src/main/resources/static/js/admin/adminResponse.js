/**
 * 응답
 * @param {string} rst
 * @param msg
 */
function adminReloadResponse(rst, msg) {
    if (rst === TRUE) {
        alert(msg);
        location.reload();
    }
}

/**
 * 금지 URL 등록 응답
 * @param {string} rst
 * @param {string} msg
 */
function adminCreateBanUrlResponse(rst, msg) {
    if (rst === TRUE) {
        location.reload();
    } else {
        $('#register-ban-url-help').html(msg);
    }
}

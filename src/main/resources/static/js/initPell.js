var editor = window.pell.init({
    element: document.getElementById('pell'),
    defaultParagraphSeparator: 'p',
    styleWithCSS: true,
    onChange: function (html) {
        var el = document.getElementById('postContent');
        el.value = html;
    },
    actions: [
        'bold',
        'italic',
        'underline',
        'paragraph',
        'code',
        'line',
        'link',
        'image'
    ]
})
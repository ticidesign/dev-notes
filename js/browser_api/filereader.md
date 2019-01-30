#FileReader API

The FileReader API allows you to access user files and their content from their machine without needing to upload to your server.

The FileReader API works off of the [File API](https://davidwalsh.name/file-api) premise and thus requires a input[type="file"] element:

```js
<input type="file" id="upload-file" onChange={handleImageChange} />
```

This example uses `FileReader`'s `readAsDataURL` method to convert the file contents to a base64-encoded string which can be used as an image data URI for the src attribute.  Other `FileReader` read types include `readAsText`, `readAsArrayBuffer`, and `readAsBinaryString`.


```js
	function handleImageChange(e) {
		if (!window.FileReader) {
			return alert('File reader not supported by browser.');
		}

		var reader = new FileReader();
		var file = e.target.files[0];
		if (!file) return;

		// TODO: limit the size and type of the image before upload

		reader.readAsDataURL(file);
		reader.onloadstart = () => console.log('Loading...');
		reader.onloadend = upload => {
			console.log(file);
			console.log(upload.target.result);
		};
	}
```
With this API you can avoid server uploads of raw user files, which I love.  You can also pre-treat content before you manually upload user content to your servers.

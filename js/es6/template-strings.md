# Template Strings (Interpolated Literals)
"Interpoliterals"

## Tagged Template String
One feature that comes along with template literals, or template strings, is the ability to tag them.

That means is that we can run a template string through a function, and rather than have the browser immediately assign that value to a variable, we can have control over how this actual string is made.


```js
function upper(strings,...values) {
	var s = "";
	for (let i=0; i<strings.length; i++) {
		if (i > 0) {
			s += String(values[i-1]).toUpperCase();
		}
		s += strings[i];
	}
	return s;
}

var name = "Ticiana",
	twitter = "ticidesign",
	topic = "React";

console.log(
	upper`Hello ${name} (@${twitter}), welcome to ${topic}!` ===
	"Hello TICIANA (@TICIDESIGN), welcome to REACT!"
);
```

```js
function highlight() {
   let str = '';
   strings.forEach((string, i) => {
       str += `${string} <span class='hl'>${values[i] || ''}</span>`;
   });
   return str;
}

const name = 'Snickers';
const age = '100';
const sentence = highlight`My dog's name is ${name} and he is ${age} years old`;
console.log(sentence)
```

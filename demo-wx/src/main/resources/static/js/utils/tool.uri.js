window.tool = window.tool || {};
window.tool.uri = (function() {
	function parseUri(uri) {
		var reg = /^\s*(((([^:\/#\?]+:)?(?:(\/\/)((?:(([^:@\/#\?]+)(?:\:([^:@\/#\?]+))?)@)?(([^:\/#\?\]\[]+|\[[^\/\]@#?]+\])(?:\:([0-9]+))?))?)?)?((\/?(?:[^\/\?#]+\/+)*)([^\?#]*)))?(\?[^#]+)?)(#.*)?/;
		var matches = reg.exec(uri || '') || [];
		return {
			href : matches[0] || '',
			hrefNoHash : matches[1] || '',
			hrefNoSearch : matches[2] || '',
			domain : matches[3] || '',
			protocol : matches[4] || '',
			doubleSlash : matches[5] || '',
			authority : matches[6] || '',
			username : matches[8] || '',
			password : matches[9] || '',
			host : matches[10] || '',
			hostname : matches[11] || '',
			port : matches[12] || '',
			pathname : matches[13] || '',
			directory : matches[14] || '',
			filename : matches[15] || '',
			search : matches[16] || '',
			hash : matches[17] || ''
		};
	}

	var OBJ_PARAM_SEQ_KEY = '__sseq';
	var OBJ_PARAM_PREFIX = '__sparam';
	function buildObjParamKey() {
		var seq = parseInt(sessionStorage.getItem(OBJ_PARAM_SEQ_KEY)) || 0;
		seq++;
		sessionStorage.setItem(OBJ_PARAM_SEQ_KEY, seq);
		return OBJ_PARAM_PREFIX + seq;
	}

	function storeObjParam(val) {
		var key = buildObjParamKey();
		sessionStorage.setItem(key, JSON.stringify(val));
		return key;
	}

	function loadObjParam(key) {
		var value = sessionStorage.getItem(key);
		try {
			return JSON.parse(value);
		} catch (e) {
			return null;
		}
	}

	function encodeParamValue(value) {
		if (!value) {
			return value;
		}
		if (value && typeof value == 'object') {
			value = storeObjParam(value);
		}
		return encodeURIComponent(value);
	}

	function decodeParamValue(value) {
		if (!value) {
			return value;
		}
		value = decodeURIComponent(value);
		if (value.indexOf(OBJ_PARAM_PREFIX) === 0) {
			value = loadObjParam(value);
		}
		return value;
	}

	function foreachParam(search, callback) {
		var params = search ? search.substring(1).split('&') : [];
		var delParamIndexs = [];
		for (var i = 0; i < params.length; i++) {
			var param = params[i].split('=');
			var name = decodeURIComponent(param[0]);
			var value = param.length > 1 ? decodeParamValue(param.slice(1)
					.join('=')) : true;
			var prevent = callback(name, value, function(newValue) {
				if (!newValue && newValue !== '') {
					delParamIndexs.push(i);
				} else if (newValue === true) {
					params[i] = param[0];
				} else {
					params[i] = param[0] + '=' + encodeParamValue(newValue);
				}
			});
			if (prevent === false) {
				break;
			}
		}
		if (!delParamIndexs.length) {
			return params;
		}
		var resultParams = [];
		var delIndex = 0;
		for (var i = 0; i < params.length; i++) {
			if (i == delParamIndexs[delIndex]) {
				delIndex++;
			} else {
				resultParams.push(params[i]);
			}
		}
		return resultParams;
	}

	function cloneObject(obj) {
		var clonedObj = {};
		for ( var key in obj) {
			clonedObj[key] = obj[key];
		}
		return clonedObj;
	}

	function indexOfArray(arr, elem) {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i] == elem) {
				return i;
			}
		}
		return -1;
	}

	function setParam(params, uri) {
		var parsedUri = parseUri(uri || location.href);
		var newParams = foreachParam(parsedUri.search, function(n, v, update) {
			if (n in params) {
				update(params[n]);
				delete params[n];
			}
		});
		for ( var n in params) {
			n = encodeURIComponent(n);
			var v = params[n];
			if (v === true) {
				newParams.push(n);
			} else if (v || v === '') {
				newParams.push(n + '=' + encodeParamValue(v));
			}
		}
		return parsedUri.hrefNoSearch + '?' + newParams.join('&')
				+ parsedUri.hash;
	}

	function getParam(names, uri) {
		var search = uri ? parseUri(uri).search : location.search;
		if (!search) {
			return {};
		}
		var params = {};
		foreachParam(search, function(n, v) {
			if (indexOfArray(names, n) >= 0) {
				params[n] = v;
			}
		});
		return params;
	}

	function getAllParam(uri) {
		var search = uri ? parseUri(uri).search : location.search;
		if (!search) {
			return {};
		}
		var params = {};
		foreachParam(search, function(n, v) {
			params[n] = v;
		});
		return params;
	}

	return {
		parseUri : parseUri,
		setParam : function(name, value, uri) {
			if (typeof name === 'string' || name instanceof String) {
				var params = {};
				params[name] = value;
				return setParam(params, uri);
			} else {
				return setParam(cloneObject(name), value);
			}
		},
		getParam : function(name, uri) {
			if (typeof name === 'string' || name instanceof String) {
				return getParam([ name ], uri)[name];
			} else if (name instanceof Array) {
				return getParam(name, uri);
			} else {
				return getAllParam(uri);
			}
		}
	};
})();
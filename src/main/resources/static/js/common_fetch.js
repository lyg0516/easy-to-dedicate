export function fetchApi(endpoint, method = 'GET', body = null) {
  if (endpoint[0] !== '/') {
    endpoint = `/${endpoint}`;
  }
  const url = `/api${endpoint}`;
  const headers = { 'Content-Type': 'application/json' };
  const config = { method, headers };
  if (body !== null) {
    config['body'] = JSON.stringify(body)
  }
  return fetch(url, config)
      .then(async (res) => {
          const statusCode = res.status;
          if (res.ok) {
              const location = res.headers.get('location');
              console.log('Location 헤더의 값:', location);
              if (res.status !== 204 && res.body != null && res.body != undefined) {
                  return await res.json();
              }
          } else {
              return { statusCode: statusCode };
          }
      })
      .catch((error) => {
          console.error(`fetch error : ${error}`);
      });
}

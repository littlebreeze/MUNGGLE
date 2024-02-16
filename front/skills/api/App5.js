import axios from 'axios';
export const edit = () => {
  // eslint-disable-next-line no-undef
  axios
    .put(
      'http://i10a410.p.ssafy.io:8080/dogs/2',
      {
        dto: {
          kindId: 57,
          birthDate: '2022-02-02T20:20:20',
          size: null,
          weight: null,
          gender: null,
          isNeutering: true,
          name: '삐삐222',
          description: '삐삐',
        },
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    )
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
};

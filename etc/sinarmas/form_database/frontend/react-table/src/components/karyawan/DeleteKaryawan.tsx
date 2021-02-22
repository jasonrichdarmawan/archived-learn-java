import React from "react";
import { environment } from "../../environments/environment";

const DeleteKaryawan = () => {
  const [id, setID] = React.useState<string>("");

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setID(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    fetch(`${environment.httpBaseUrl}/v1/karyawan/${id}`, {
      method: "DELETE",
    }).then(async (response) => {
      if (response.status === 200) {
        const body = await response.text();
        console.log(body);
      }
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        ID Karyawan
        <input
          type="text"
          name="id"
          required
          onChange={handleChange}
          pattern="[0-9]{1,}"
        />
      </label>
      <input type="submit" value="submit" />
    </form>
  );
};

export default DeleteKaryawan;

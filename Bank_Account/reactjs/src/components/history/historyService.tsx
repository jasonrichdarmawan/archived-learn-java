export interface getTransactionsRequestBody {
  Start: string;
  End: string;
}

export interface TransactionModel {
  Date: string;
  Source: string;
  Destination: string;
  Destination_Type: number;
  transaction_Value: number;
  Ending_Balance: number;
}

export interface getTransactionsResponseBody {
  message_code: number;
  message: string;
  Opening_Balance: number;
  transactions: TransactionModel[];
}

export default async function getTransactions({
  Start,
  End,
}: getTransactionsRequestBody) {
  const response = await fetch("http://localhost:8080/api/v1/history", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
    body: JSON.stringify({
      Start,
      End,
    }),
  });
  return await response
    .json()
    .then((data: getTransactionsResponseBody) => data);
}

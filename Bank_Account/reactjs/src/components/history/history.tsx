import React from "react";
import HistorySelect from "./historyselectComponent";
import { TransactionModel } from "./historyService";
import Transactions from "./transactionsComponent";

export default function History() {
  const [isTransactionsShown, setIsTransactionsShown] = React.useState(false);

  const [transactions, setTransactions] = React.useState<TransactionModel[]>(
    []
  );

  return (
    <>
      {!isTransactionsShown ? (
        <HistorySelect
          setIsTransactionsShown={setIsTransactionsShown}
          setTransactions={setTransactions}
        />
      ) : (
        <Transactions transactions={transactions} />
      )}
    </>
  );
}

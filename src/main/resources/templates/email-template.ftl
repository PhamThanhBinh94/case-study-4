<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div style="background-color: #15161D; width: 100%; padding: 8px">
    <p style="color: #f5f5f5; text-align: center; font-family: Arial, sans-serif; font-size: xx-large">THÔNG TIN ĐƠN HÀNG</p>
</div>
<div style="font-family: Arial; padding: 5px">
    <p style="margin: 5px">Cám ơn quý khách ${customerName}</p>
    <p style="margin: 5px">Electro rất vui thông báo đơn hàng #${billId} của quý khách đã được tiếp nhận và đang trong quá trình xử lý.</p>
    <p style="margin: 5px">Electro sẽ thông báo đến quý khách ngay khi hàng chuẩn bị được giao.</p>
    <p style="margin: 5px">Địa chỉ giao hàng: ${customerAddress}</p>
    <p style="margin: 5px">SĐT người nhận: ${customerPhone}</p>
</div>
<div style="padding: 5px">
    <table width="100%" style="font-family: Arial; text-align: center">
        <tr style="background-color: #D10024; color: #f5f5f5; padding: 10px; border: 1px solid black">
            <th width="30%">Sản phẩm</th>
            <th width="40%">Mô tả</th>
            <th width="15%">Số lượng</th>
            <th width="15%">Đơn giá</th>
        </tr>
        ${billDetails}
    </table>
    <p style="font-weight: bold">Tổng tiền: ${total}</p>
</div>
<div style="background-color: #15161D; color: #f5f5f5; font-family: Arial">
    <p style="padding: 2px">Quý khách nhận được email này vì đã mua hàng tại Electro.</p>
    <p style="padding: 2px">Bạn cần được hỗ trợ ngay? Chỉ cần email hotro@electro.com, hoặc gọi số điện thoại 1900-6035 (8-21h cả T7,CN). Đội ngũ Electro Care luôn sẵn sàng hỗ trợ bạn bất kì lúc nào.</p>
</div>
</body>
</html>
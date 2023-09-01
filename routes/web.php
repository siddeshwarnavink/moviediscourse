<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Auth\CreateAccountController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});


Route::get('/auth/create-account.php', [CreateAccountController::class, 'view'])->name('auth.createaccount.view');
Route::post('/auth/create-account.php', [CreateAccountController::class, 'action'])->name('auth.createaccount.action');

// Route::get('/auth/login.php', function () {
//     return view('auth.createaccount');
// });
<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Auth\CreateAccountController;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Auth\LogoutController;

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

Route::get('/', function() {
    return redirect('/home.php');
});

Route::get('/home.php', function () {
    return view('home');
});


Route::get('/auth/create-account.php', [CreateAccountController::class, 'view'])->name('auth.createaccount.view');
Route::post('/auth/create-account.php', [CreateAccountController::class, 'action'])->name('auth.createaccount.action');
Route::get('/auth/login.php', [LoginController::class, 'view'])->name('auth.login.view');
Route::post('/auth/login.php', [LoginController::class, 'action'])->name('auth.login.action');
Route::get('/auth/logout.php', [LogoutController::class, 'action'])->name('auth.logout');